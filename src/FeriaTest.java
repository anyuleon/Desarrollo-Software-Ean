import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

public class FeriaTest {

    @Test
    public void testRegistrarEmpresa() {
        Feria feria = new Feria();
        Empresa empresa = new Empresa("TechCorp", "Tecnología", "info@techcorp.com");
        feria.registrarEmpresa(empresa);
        assertEquals(1, feria.getEmpresas().size());
    }

    @Test
    public void testAsignarStand() {
        Feria feria = new Feria();
        Empresa empresa = new Empresa("TechCorp", "Tecnología", "info@techcorp.com");
        Stand stand = new Stand(1, "Pabellón A, Stand 10", "Grande");
        feria.asignarStand(empresa, stand);
        assertEquals(empresa, stand.getEmpresaAsignada());
    }

    @Test
    public void testCalcularCalificacionPromedio() {
        Feria feria = new Feria();
        Stand stand = new Stand(1, "Pabellón A, Stand 10", "Grande");
        Visitante visitante1 = new Visitante("Juan", "12345", "juan@example.com");
        Visitante visitante2 = new Visitante("Maria", "67890", "maria@example.com");
        feria.agregarComentario(new Comentario(visitante1, stand, new Date(), 4));
        feria.agregarComentario(new Comentario(visitante2, stand, new Date(), 5));
        assertEquals(4.5, feria.calcularCalificacionPromedio(stand), 0.01);
    }

     @Test
    public void testEditarEmpresa() {
        Feria feria = new Feria();
        Empresa empresa = new Empresa("TechCorp", "Tecnología", "info@techcorp.com");
        feria.registrarEmpresa(empresa);

        // Editar la empresa
        boolean resultado = feria.editarEmpresa("TechCorp", "TechCorp Editada", "Software", "nuevo@techcorp.com");
        assertTrue("La empresa debería haberse editado correctamente.", resultado);

        // Verificar que los datos se actualizaron
        Empresa empresaEditada = feria.getEmpresas().get(0);
        assertEquals("TechCorp Editada", empresaEditada.getNombre());
        assertEquals("Software", empresaEditada.getSector());
        assertEquals("nuevo@techcorp.com", empresaEditada.getEmail());
    }

    @Test
    public void testEliminarEmpresa() {
        Feria feria = new Feria();
        Empresa empresa = new Empresa("TechCorp", "Tecnología", "info@techcorp.com");
        feria.registrarEmpresa(empresa);

        // Eliminar la empresa
        boolean resultado = feria.eliminarEmpresa("TechCorp");
        assertTrue("La empresa debería haberse eliminado correctamente.", resultado);

        // Verificar que la lista de empresas está vacía
        assertEquals(0, feria.getEmpresas().size());
    }
}