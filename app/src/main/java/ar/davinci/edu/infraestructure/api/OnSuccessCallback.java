package ar.davinci.edu.infraestructure.api;

public interface OnSuccessCallback {

    public void execute(Object body);
    public void error(Object body);
}