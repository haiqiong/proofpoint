package proofpoint;

public class ZipFile extends CompositeEntity{

	public int getSize() {
		return super.getSize() / 2;
	}
}
