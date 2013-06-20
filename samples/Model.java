import javax.swing.DefaultListModel;

public class Model extends DefaultListModel<String> {
	/**
	* Constructs a DefaultComboBoxModel object.
	*/
	public Model() {
    	for (String s : new String[] {"Bird", "Cat", "Dog", "Rabbit", "Pig"} ) {
			this.addElement(s);
		}
	}
}