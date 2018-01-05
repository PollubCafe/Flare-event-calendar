package pl.pollub.cs.pentagoncafe.flare.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(Class classOfObject, String objectId){
        super(new StringBuilder(classOfObject.getSimpleName()).append(" with id: ").append(objectId).append(" not found").toString());
    }

    public ObjectNotFoundException(String classOfObject, String objectId){
        super(new StringBuilder(classOfObject).append(" with id: ").append(objectId).append(" not found").toString());
    }

    public ObjectNotFoundException(Class classOfObject, String fieldName, String fieldValue){
        super(new StringBuilder(classOfObject.getSimpleName()).append(" with ").append(fieldName).append(" : ").append(fieldValue).append(" not found").toString());
    }

    public ObjectNotFoundException(String classOfObject, String fieldName, String fieldValue){
        super(new StringBuilder(classOfObject).append(" with ").append(fieldName).append(" : ").append(fieldValue).append(" not found").toString());
    }

    public ObjectNotFoundException(String msg){
        super(msg);
    }
}
