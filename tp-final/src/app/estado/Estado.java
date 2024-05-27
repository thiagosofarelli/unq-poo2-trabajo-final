package app.estado;

import app.AppEstacionamiento;

public abstract class Estado {
	
	protected Estado proximoEstado;
	
	public abstract void driving(AppEstacionamiento app);
	
	public abstract void walking(AppEstacionamiento app);
	
	public Estado getProximoEstado() {
		return this.proximoEstado;
	}
}
