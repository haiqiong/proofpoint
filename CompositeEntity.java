package proofpoint;

import java.util.ArrayList;
import java.util.List;

public class CompositeEntity extends Entity {
	protected List<Entity> container = new ArrayList<Entity>();
	
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
			int index = destPath.lastIndexOf("\\");
			String name = destPath.substring(index + 1);
			String parentPath = destPath.substring(0, index);
			
			try {
				CompositeEntity destFolder = (CompositeEntity)create(type, name, parentPath);
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
