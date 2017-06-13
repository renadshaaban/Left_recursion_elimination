import java.util.ArrayList;


public class rule {
String head;
ArrayList <String> tails;
boolean is_recursive=false;

public rule(){}
public rule(ArrayList <String> tails,String head){
	this.tails=tails;
	this.head=head;
}
public rule(boolean is_recursive){
	this.is_recursive=is_recursive;
}
public rule(String head){
	this.head=head;
}
}
