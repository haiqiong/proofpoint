package proofpoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A drive is not contained in any entity.
 * A drive may contain zero to many other folders, or files.
 * @author haiqiongyao
 *
 */
public class Driver{
	private EntityType type;
	private String name;
	private int size;
	
	static Set<String> paths = new HashSet<String>();
	private List<Entity> container = new ArrayList<Entity>();
	
	public Entity create(EntityType type, String name, String parentPath) throws
	PathNotFoundException, PathExistsException, IllegalFileSystemOperationException{
		if (!parentPath.isEmpty())  {
			throw new PathNotFoundException();
		} else if (paths.contains(path)) {
			throw new PathExistsException();
		} else {
			this.type = type;
			this.name = name;
			this.size = getSize();
			this.paths.add(name);
		}
	}

	
	public void delete(String path) throws
	PathNotFoundException {
		if (!paths.contains(path)) {
			throw new PathNotFoundException();
		} else {
			for (Entity child : container) {
				child.delete(path + "\\" + child.name);
				child = null;
			}
			paths.remove(path);
		}
	}
	public void move(String sourcePath, String destPath) throws 
	PathNotFoundException, PathExistsException, IllegalFileSystemOperationException {
		if (!path.contains(sourcePath)) {
			throw new PathNotFoundException();
		} else if (paths.contains(destPath)) {
			throw new IllegalFileSystemOperationException();
		} else if (sourcePath.equals(destPath)) {
			throw new PathExistsException();
		} else {
			try {
				CompositeEntity destFolder = (CompositeEntity)create(type, destPath, "");
				destFolder.setContainer(this.container);
				paths.remove(sourcePath);
			} catch (PathNOtFoundException e) {
				System.out.println(e.printStatckTrace());
			} catch (PathExistsException e) {
				System.out.println(e.pritnStatckTrace());
			} catch (IllegalFileSystemOperationException e) {
				System.out.println(e.printStatckTrace());
			}
		}
	}
	public int getSize() {
		int size = 0;
		for (Entity entity : container) {
			size += entity.getSize();
		}
		return size;
	}
	public void setContainer(List<Entity> container) {
		this.container = container;
	}
}
