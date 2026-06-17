package gestion.com.hotel.entitys;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Servicio {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_servicio;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToMany(mappedBy = "servicios")
    private Set<Hotel> hoteles;

    public Servicio() {

    }

    public Long getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Long id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Set<Hotel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(Set<Hotel> hoteles) {
        this.hoteles = hoteles;
    }
}
