package icai.dtc.isw.domain;

public class Cliente {

    private String nombre;
    private String correo;
    private String contra;

    public Cliente(String nombre, String contra, String correo){
        this.nombre = nombre;
        this.correo = correo;
        this.contra = contra;
    }

    //Unicamente para comparar
    public Cliente(String nombre, String contra){
        this.nombre = nombre;
        this.contra = contra;
    }

    public String getNombre(){
        return nombre;
    }

    public String getContra(){
        return contra;
    }

    public String getCorreo(){
        return correo;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Cliente){
            Cliente c = (Cliente) o;
            if (c.getNombre().equals(nombre) && c.getContra().equals(contra)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "---CLIENTE---\nNombre: " + nombre +
                ", correo: " + correo;
    }
}
