package pub.types;
 
public interface Action<T> {

	void execute(T param);

}
