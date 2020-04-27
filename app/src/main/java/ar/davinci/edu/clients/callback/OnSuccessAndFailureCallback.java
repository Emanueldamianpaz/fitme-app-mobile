package ar.davinci.edu.clients.callback;

public interface OnSuccessAndFailureCallback {

    public void execute(Object body);
    public void error(Object body);
}