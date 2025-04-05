// app.js
const express = require('express');
const bodyParser = require('body-parser');
const mysql = require('mysql2/promise');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(bodyParser.json());

// Configuración de la base de datos
const dbConfig = {
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'techLogistics'
};

// Conexión a la base de datos
let connection;
mysql.createConnection(dbConfig)
    .then(conn => {
        connection = conn;
        console.log('Conexión a MySQL establecida');
    })
    .catch(err => {
        console.error('Error al conectar a MySQL:', err);
    });

// Rutas para Clientes
app.get('/api/clientes', async (req, res) => {
    try {
        const [rows] = await connection.query('SELECT * FROM Clientes');
        res.json(rows);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Obtener un cliente por ID
app.get('/api/clientes/:id', async (req, res) => {
    try {
        const [rows] = await connection.query('SELECT * FROM Clientes WHERE cliente_id = ?', [req.params.id]);
        if (rows.length === 0) {
            return res.status(404).json({ error: 'Cliente no encontrado' });
        }
        res.json(rows[0]);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.post('/api/clientes', async (req, res) => {
    try {
        const { nombre, apellido, email, telefono, direccion, ciudad, codigo_postal } = req.body;
        const [result] = await connection.query(
            'INSERT INTO Clientes SET ?',
            { nombre, apellido, email, telefono, direccion, ciudad, codigo_postal }
        );
        res.status(201).json({ id: result.insertId });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Actualizar un cliente
app.put('/api/clientes/:id', async (req, res) => {
    try {
        const { nombre, apellido, email, telefono, direccion, ciudad, codigo_postal } = req.body;
        const [result] = await connection.query(
            'UPDATE Clientes SET ? WHERE cliente_id = ?',
            [{ nombre, apellido, email, telefono, direccion, ciudad, codigo_postal }, req.params.id]
        );
        if (result.affectedRows === 0) {
            return res.status(404).json({ error: 'Cliente no encontrado' });
        }
        res.json({ message: 'Cliente actualizado correctamente' });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Eliminar un cliente
app.delete('/api/clientes/:id', async (req, res) => {
    try {
        const [result] = await connection.query('DELETE FROM Clientes WHERE cliente_id = ?', [req.params.id]);
        if (result.affectedRows === 0) {
            return res.status(404).json({ error: 'Cliente no encontrado' });
        }
        res.json({ message: 'Cliente eliminado correctamente' });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Rutas para Productos
app.get('/api/productos', async (req, res) => {
    try {
        const [rows] = await connection.query('SELECT * FROM Productos');
        res.json(rows);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.get('/api/productos/:id', async (req, res) => {
    try {
        const [rows] = await connection.query('SELECT * FROM Productos WHERE producto_id = ?', [req.params.id]);
        if (rows.length === 0) {
            return res.status(404).json({ error: 'Producto no encontrado' });
        }
        res.json(rows[0]);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.post('/api/productos', async (req, res) => {
    try {
        const { nombre, descripcion, peso, dimensiones, precio_unitario} = req.body;
        const [result] = await connection.query(
            'INSERT INTO Productos SET ?',
            { nombre, descripcion, peso, dimensiones, precio_unitario }
        );
        res.status(201).json({ id: result.insertId });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.put('/api/productos/:id', async (req, res) => {
    try {
        const { nombre, descripcion, peso, dimensiones, precio_unitario} = req.body;
        const [result] = await connection.query(
            'UPDATE Productos SET ? WHERE producto_id = ?',
            [{ nombre, descripcion, peso, dimensiones, precio_unitario }, req.params.id]
        );
        if (result.affectedRows === 0) {
            return res.status(404).json({ error: 'Producto no encontrado' });
        }
        res.json({ message: 'Producto actualizado correctamente' });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.delete('/api/productos/:id', async (req, res) => {
    try {
        const [result] = await connection.query('DELETE FROM Productos WHERE producto_id = ?', [req.params.id]);
        if (result.affectedRows === 0) {
            return res.status(404).json({ error: 'Producto no encontrado' });
        }
        res.json({ message: 'Producto eliminado correctamente' });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Rutas para Pedidos
app.get('/api/pedidos', async (req, res) => {
    try {
        const [rows] = await connection.query(`
            SELECT 
                p.pedido_id,
                p.cliente_id,
                p.fecha_pedido,
                p.fecha_entrega_estimada,
                CAST(p.total AS DECIMAL(12,2)) as total,
                c.nombre as cliente_nombre, 
                c.apellido as cliente_apellido 
            FROM Pedidos p
            JOIN Clientes c ON p.cliente_id = c.cliente_id
        `);

        const pedidos = rows.map(pedido => ({
            ...pedido,
            total: Number(pedido.total)
        }));

        res.json(pedidos);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.get('/api/pedidos/:id', async (req, res) => {
    try {
        const [pedido] = await connection.query(`
            SELECT 
                p.*,
                c.nombre as cliente_nombre,
                c.apellido as cliente_apellido
            FROM Pedidos p
            JOIN Clientes c ON p.cliente_id = c.cliente_id
            WHERE p.pedido_id = ?
        `, [req.params.id]);

        if (pedido.length === 0) {
            return res.status(404).json({ error: 'Pedido no encontrado' });
        }

        const [productos] = await connection.query(`
            SELECT 
                pp.*,
                p.nombre as producto_nombre,
                p.descripcion as producto_descripcion
            FROM Pedidos_Productos pp
            JOIN Productos p ON pp.producto_id = p.producto_id
            WHERE pp.pedido_id = ?
        `, [req.params.id]);

        res.json({
            ...pedido[0],
            productos
        });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.post('/api/pedidos', async (req, res) => {
    try {
        const { cliente_id, productos, fecha_entrega_estimada, total } = req.body;
        
        await connection.beginTransaction();
        
        const [pedidoResult] = await connection.query(
            'INSERT INTO Pedidos SET ?',
            { cliente_id, fecha_entrega_estimada, total }
        );
        const pedido_id = pedidoResult.insertId;
        
        for (const producto of productos) {
            await connection.query(
                'INSERT INTO Pedidos_Productos SET ?',
                { pedido_id, producto_id: producto.id, cantidad: producto.cantidad, precio_unitario: producto.precio }
            );
        }
        
        await connection.commit();
        
        res.status(201).json({ id: pedido_id });
    } catch (err) {
        await connection.rollback();
        res.status(500).json({ error: err.message });
    }
});

app.put('/api/pedidos/:id', async (req, res) => {
    try {
        const { fecha_entrega_estimada, total } = req.body;
        
        await connection.beginTransaction();
        
        const [result] = await connection.query(
            'UPDATE Pedidos SET ? WHERE pedido_id = ?',
            [{ fecha_entrega_estimada, total }, req.params.id]
        );
        
        if (result.affectedRows === 0) {
            await connection.rollback();
            return res.status(404).json({ error: 'Pedido no encontrado' });
        }
        
        await connection.commit();
        res.json({ message: 'Pedido actualizado correctamente' });
    } catch (err) {
        await connection.rollback();
        res.status(500).json({ error: err.message });
    }
});

app.delete('/api/pedidos/:id', async (req, res) => {
    try {
        await connection.beginTransaction();
        
        // Primero eliminar los productos del pedido
        await connection.query('DELETE FROM Pedidos_Productos WHERE pedido_id = ?', [req.params.id]);
        
        // Luego eliminar el pedido
        const [result] = await connection.query('DELETE FROM Pedidos WHERE pedido_id = ?', [req.params.id]);
        
        if (result.affectedRows === 0) {
            await connection.rollback();
            return res.status(404).json({ error: 'Pedido no encontrado' });
        }
        
        await connection.commit();
        res.json({ message: 'Pedido eliminado correctamente' });
    } catch (err) {
        await connection.rollback();
        res.status(500).json({ error: err.message });
    }
});

// Rutas para Envíos
app.get('/api/envios', async (req, res) => {
    try {
        const [rows] = await connection.query(`
            SELECT 
                e.*,
                es.nombre as estado,
                t.nombre as transportista,
                r.origen,
                r.destino
            FROM Envios e
            JOIN Estados_Envio es ON e.estado_id = es.estado_id
            JOIN Transportistas t ON e.transportista_id = t.transportista_id
            JOIN Rutas r ON e.ruta_id = r.ruta_id
        `);
        res.json(rows);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.get('/api/envios/:id', async (req, res) => {
    try {
        const [envio] = await connection.query(`
            SELECT 
                e.*,
                es.nombre as estado,
                t.nombre as transportista,
                r.origen,
                r.destino,
                r.distancia_km,
                r.tiempo_estimado_horas
            FROM Envios e
            JOIN Estados_Envio es ON e.estado_id = es.estado_id
            JOIN Transportistas t ON e.transportista_id = t.transportista_id
            JOIN Rutas r ON e.ruta_id = r.ruta_id
            WHERE e.envio_id = ?
        `, [req.params.id]);
        
        if (envio.length === 0) {
            return res.status(404).json({ error: 'Envío no encontrado' });
        }
        
        const [ubicaciones] = await connection.query(`
            SELECT * FROM Ubicaciones_Envio 
            WHERE envio_id = ? 
            ORDER BY fecha_hora DESC
        `, [req.params.id]);
        
        res.json({
            ...envio[0],
            ubicaciones,
            historial: await obtenerHistorialEstados(req.params.id)
        });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

app.post('/api/envios', async (req, res) => {
    try {
        const { pedido_id, transportista_id, ruta_id, estado_id } = req.body;
        
        await connection.beginTransaction();
        
        const [result] = await connection.query(
            'INSERT INTO Envios SET ?',
            { pedido_id, transportista_id, ruta_id, estado_id }
        );
        
        await connection.commit();
        res.status(201).json({ id: result.insertId });
    } catch (err) {
        await connection.rollback();
        res.status(500).json({ error: err.message });
    }
});

app.put('/api/envios/:id', async (req, res) => {
    try {
        const { transportista_id, ruta_id, estado_id } = req.body;
        
        await connection.beginTransaction();
        
        const [result] = await connection.query(
            'UPDATE Envios SET ? WHERE envio_id = ?',
            [{ transportista_id, ruta_id, estado_id }, req.params.id]
        );
        
        if (result.affectedRows === 0) {
            await connection.rollback();
            return res.status(404).json({ error: 'Envío no encontrado' });
        }
        
        await connection.commit();
        res.json({ message: 'Envío actualizado correctamente' });
    } catch (err) {
        await connection.rollback();
        res.status(500).json({ error: err.message });
    }
});

app.delete('/api/envios/:id', async (req, res) => {
    try {
        await connection.beginTransaction();
        
        // Primero eliminar las ubicaciones del envío
        await connection.query('DELETE FROM Ubicaciones_Envio WHERE envio_id = ?', [req.params.id]);
        
        // Luego eliminar el envío
        const [result] = await connection.query('DELETE FROM Envios WHERE envio_id = ?', [req.params.id]);
        
        if (result.affectedRows === 0) {
            await connection.rollback();
            return res.status(404).json({ error: 'Envío no encontrado' });
        }
        
        await connection.commit();
        res.json({ message: 'Envío eliminado correctamente' });
    } catch (err) {
        await connection.rollback();
        res.status(500).json({ error: err.message });
    }
});

// Ruta para seguimiento de envíos
app.get('/api/envios/:codigo/seguimiento', async (req, res) => {
    try {
        const codigo = req.params.codigo;
        const [envio] = await connection.query(`
            SELECT e.*, es.nombre as estado, t.nombre as transportista, 
                   r.origen, r.destino, r.distancia_km, r.tiempo_estimado_horas
            FROM Envios e
            JOIN Estados_Envio es ON e.estado_id = es.estado_id
            JOIN Transportistas t ON e.transportista_id = t.transportista_id
            JOIN Rutas r ON e.ruta_id = r.ruta_id
            WHERE e.codigo_seguimiento = ?
        `, [codigo]);
        
        if (envio.length === 0) {
            return res.status(404).json({ error: 'Envío no encontrado' });
        }
        
        const [ubicaciones] = await connection.query(`
            SELECT * FROM Ubicaciones_Envio 
            WHERE envio_id = ? 
            ORDER BY fecha_hora DESC
        `, [envio[0].envio_id]);
        
        res.json({
            envio: envio[0],
            ubicaciones,
            historial: await obtenerHistorialEstados(envio[0].envio_id)
        });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Función auxiliar para obtener historial de estados
async function obtenerHistorialEstados(envio_id) {
    try {
        const [historial] = await connection.query(`
            SELECT 
                he.*,
                es.nombre as estado
            FROM Historial_Estados he
            JOIN Estados_Envio es ON he.estado_id = es.estado_id
            WHERE he.envio_id = ?
            ORDER BY he.fecha_cambio DESC
        `, [envio_id]);
        return historial;
    } catch (err) {
        console.error('Error al obtener historial:', err);
        return [];
    }
}

// Rutas para Transportistas
app.get('/api/transportistas', async (req, res) => {
    try {
        const [rows] = await connection.query('SELECT * FROM Transportistas');
        res.json(rows);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Rutas para Rutas
app.get('/api/rutas', async (req, res) => {
    try {
        const [rows] = await connection.query('SELECT * FROM Rutas');
        res.json(rows);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Rutas para Estados
app.get('/api/estados', async (req, res) => {
    try {
        const [rows] = await connection.query('SELECT * FROM Estados_Envio');
        res.json(rows);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// Iniciar servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Servidor API corriendo en puerto ${PORT}`);
});