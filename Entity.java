package proofpoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Applying composite pattern to implement a file system.
 * Entity is the component class. 
 * TextFile is the leaf.
 * Driver, Folder, ZipFile are composite class. I did not implement the methods
 * for adding child components of the composite object.
 * @author haiqiongyao
 * 4/8/2013
 */

public abstract class Entity {
	protected EntityType type;
	protected String name;
	protected int size;
	
	static Set<String> paths = new HashSet<String>();
	
	public abstract void delete(String path);
	public abstract void move(String sourcePath, String destPath);
	public abstract int getSize();
	
	public Entity create(EntityType type, String name, String parentPath) throws
	PathNotFoundException, PathExistsException, IllegalFileSystemOperationException{
		String path = parentPath + "\\" + name;
		if (parentPath.isEmpty())  {
			throw new PathNotFoundException();
		} else if (paths.contains(path)) {
			throw new PathExistsException();
		} else {
			this.type = type;
			this.name = name;
			this.size = getSize();
			this.paths.add(path);
		}
	}
	
	
}
