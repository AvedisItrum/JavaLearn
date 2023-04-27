package Practice.MyImplementations;

public interface ChangesSavable {
    void undo(int i);

    void undo();

    void redo(int i);

    void redo();
}
