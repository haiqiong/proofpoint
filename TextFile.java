package proofpoint;

public class TextFile extends Entity{
	private String content;
	
	public void delete(String path) throws
	PathNotFoundException {
		if (!paths.contains(path)) {
			throw new PathNotFoundException();
		} else {
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
				Entity destFolder = create(type, name, parentPath);
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
		return content.length();
	}
	public void writeToFile(String path, String content) throws
	PathNotFoundException, NotTextFileException {
		if (type != EntityType.TEXTFILE) {
			throw new NotTextFileException();
		} else if (!paths.contains(path)) {
			throw new PathNotFoundException();
		} else {
			this.content = content;
		}
	}
	
}
