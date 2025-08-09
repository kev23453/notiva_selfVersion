package Modules;

public abstract class elemento {
	protected String titulo;
	protected String descripcion;
	
	public elemento(String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	
	public abstract void mostrarElementos();
	public abstract void crear();
	public abstract void eliminar();
	public abstract void editar();
}
