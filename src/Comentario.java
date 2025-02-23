import java.util.Date;

public class Comentario {
    private Visitante visitante;
    private Stand stand;
    private Date fecha;
    private int calificacion;

    // Constructor
    public Comentario(Visitante visitante, Stand stand, Date fecha, int calificacion) {
        this.visitante = visitante;
        this.stand = stand;
        this.fecha = fecha;
        this.calificacion = calificacion;
    }

    // Getters y Setters
    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
