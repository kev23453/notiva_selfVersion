package Modules;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tarea extends elemento{
	private int visibilidad;
	private int categoria;
	private String fechaExpiracion;
	
	public Tarea(String titulo, String descripcion, int visibilidad, int categoria, String fechaExpiracion) {
		super(titulo, descripcion);
		this.visibilidad = visibilidad;
		this.categoria = categoria;
		this.fechaExpiracion = fechaExpiracion;
	}
	
	public void mostrarElementos() {
		
	}
	
	public void crear() {
		try {
			if(titulo == "") {
				System.out.println("titulo de la tarea debe ser obligatorio");
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ha ocurrido un error al crear el elemento:" + e.getMessage());
		}
	}
	
	public void eliminar() {
		
	}
	
	public void editar() {
		
	}
	
	
	

	
	
}
