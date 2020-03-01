package ar.davinci.edu.api.clients;

public interface OnSuccessAndFailureCallback {

    public void execute(Object body);
    public void error(Object body);
}