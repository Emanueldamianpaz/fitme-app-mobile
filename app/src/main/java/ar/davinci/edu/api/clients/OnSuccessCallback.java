package ar.davinci.edu.api.clients;

public interface OnSuccessCallback {

    public void execute(Object body);
    public void error(Object body);
}