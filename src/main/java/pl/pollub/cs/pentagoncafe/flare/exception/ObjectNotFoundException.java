package pl.pollub.cs.pentagoncafe.flare.exception;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(Class classOfObject){
        super(classOfObject.getSimpleName() + " not found");
    }

    public ObjectNotFoundException(Class classOfObject, Long objectId){
        super(classOfObject.getSimpleName() + " with id: " + objectId + " not found");
    }

    public ObjectNotFoundException(String classOfObject, String objectId){
        super(classOfObject + " with id: " + objectId + " not found");
    }

    public ObjectNotFoundException(Class classOfObject, String fieldName, String fieldValue){
        super(classOfObject.getSimpleName() + " with " + fieldName + " : " + fieldValue + " not found");
    }

    public ObjectNotFoundException(String classOfObject, String fieldName, String fieldValue){
        super(classOfObject + " with " + fieldName + " : " + fieldValue + " not found");
    }

    public ObjectNotFoundException(String msg){
        super(msg);
    }
}
