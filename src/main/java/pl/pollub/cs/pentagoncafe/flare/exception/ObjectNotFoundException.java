package pl.pollub.cs.pentagoncafe.flare.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(Class classOfObject, String objectId){
        super(new StringBuilder(classOfObject.getSimpleName()).append(" with id: ").append(objectId).append(" not found").toString());
    }

    public ObjectNotFoundException(String msg){
        super(msg);
    }
}
