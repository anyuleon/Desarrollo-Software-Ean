import java.util.ArrayList;
import java.util.List;

public class Feria {
    private List<Empresa> empresas;
    private List<Stand> stands;
    private List<Visitante> visitantes;
    private List<Comentario> comentarios;

    // Constructor
    public Feria() {
        empresas = new ArrayList<>();
        stands = new ArrayList<>();
        visitantes = new ArrayList<>();
        comentarios = new ArrayList<>();
    }

    // Métodos para gestionar empresas
    public void registrarEmpresa(Empresa empresa) {
        empresas.add(empresa);
    }
    
    // Método para editar una empresa
    public boolean editarEmpresa(String nombreEmpresa, String nuevoNombre, String nuevoSector, String nuevoEmail) {
        for (Empresa empresa : empresas) {
            if (empresa.getNombre().equals(nombreEmpresa)) {
                empresa.setNombre(nuevoNombre);
                empresa.setSector(nuevoSector);
                empresa.setEmail(nuevoEmail);
                return true; // Empresa editada con éxito
            }
        }
        return false; // Empresa no encontrada
    }

    // Método para eliminar una empresa
    public boolean eliminarEmpresa(String nombreEmpresa) {
        for (Empresa empresa : empresas) {
            if (empresa.getNombre().equals(nombreEmpresa)) {
                empresas.remove(empresa);
                return true; // Empresa eliminada con éxito
            }
        }
        return false; // Empresa no encontrada
    }

    // Métodos para gestionar stands
    public void asignarStand(Empresa empresa, Stand stand) {
        stand.setEmpresaAsignada(empresa);
        stands.add(stand);
    }

    public List<Stand> listarStandsDisponibles() {
        List<Stand> disponibles = new ArrayList<>();
        for (Stand stand : stands) {
            if (stand.getEmpresaAsignada() == null) {
                disponibles.add(stand);
            }
        }
        return disponibles;
    }

    // Métodos para gestionar visitantes
    public void registrarVisitante(Visitante visitante) {
        visitantes.add(visitante);
    }

    public void eliminarVisitante(Visitante visitante) {
        visitantes.remove(visitante);
    }

    // Métodos para gestionar comentarios
    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    // Métodos para generar reportes
    public void generarReporteEmpresas() {
        for (Empresa empresa : empresas) {
            System.out.println("Empresa: " + empresa.getNombre() + ", Sector: " + empresa.getSector() + ", Email: " + empresa.getEmail());
        }
    }

    public void generarReporteVisitantes() {
        for (Visitante visitante : visitantes) {
            System.out.println("Visitante: " + visitante.getNombre() + ", ID: " + visitante.getId() + ", Email: " + visitante.getEmail());
        }
    }

    public void generarReporteCalificaciones() {
        for (Stand stand : stands) {
            double promedio = calcularCalificacionPromedio(stand);
            System.out.println("Stand: " + stand.getNumero() + ", Ubicación: " + stand.getUbicacion() + ", Calificación Promedio: " + promedio);
        }
    }

    public double calcularCalificacionPromedio(Stand stand) {
        int totalCalificaciones = 0;
        int cantidadComentarios = 0;
        for (Comentario comentario : comentarios) {
            if (comentario.getStand().equals(stand)) {
                totalCalificaciones += comentario.getCalificacion();
                cantidadComentarios++;
            }
        }
        return cantidadComentarios > 0 ? (double) totalCalificaciones / cantidadComentarios : 0;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public List<Stand> getStands(){
        return stands;
    }

    public List<Visitante> getVisitantes(){
        return visitantes;
    }
}