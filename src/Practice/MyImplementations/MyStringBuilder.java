package Practice.MyImplementations;

import java.util.*;
import java.util.stream.IntStream;

public class MyStringBuilder implements Appendable, CharSequence, ChangesSavable, Removable {


    private static class Action {
        public CharSequence cs;
        public Boolean added;

        public Action(CharSequence cs, boolean added) {
            this.cs = cs;
            this.added = added;
        }
    }

    char[] chars;
    int undoCapacity = 30;

    LinkedList<Action> undoStorage;
    int undoIndex = -1;

    public MyStringBuilder(String str) {
        chars = str.toCharArray();
        undoStorage = new LinkedList<Action>();
    }

    public void undoAdd(CharSequence cs, Boolean added) {
        if (undoIndex != -1) {
            for (int i = 0; i <= undoIndex; i++)
                undoStorage.pop();
            undoIndex = -1;
        }
        undoStorage.push(new Action(cs, added));
        if (undoStorage.size() > undoCapacity)
            undoStorage.pollLast();
    }

    public void isFit(int length) {
        if (length > chars.length)
            chars = Arrays.copyOf(chars, length);
    }

    @Override
    public Appendable append(CharSequence csq) {
        undoAdd(csq, true);
        isFit(csq.length() + chars.length);
        System.arraycopy(csq.toString().toCharArray(), 0, chars, chars.length - csq.length(), csq.length());
        return this;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) {
        undoAdd(csq.subSequence(start, end), true);
        isFit(chars.length + (end - start));
        System.arraycopy(csq.toString().toCharArray(), start, chars, chars.length - (end - start), end - start);
        return this;
    }

    @Override
    public Appendable append(char c) {
        undoAdd(String.valueOf(c), true);
        isFit(chars.length + 1);
        System.arraycopy(new char[]{c}, 0, chars, chars.length - 1, 1);
        return this;
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public char charAt(int index) {
        return chars[index];
    }

    @Override
    public boolean isEmpty() {
        return chars.length == 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new String(chars).subSequence(start, end);
    }

    @Override
    public IntStream chars() {
        return CharSequence.super.chars();
    }

    @Override
    public IntStream codePoints() {
        return CharSequence.super.codePoints();
    }

    @Override
    public void remove(int amount) {
        undoAdd(this.subSequence(Math.max(0, chars.length - amount), chars.length), false);
        chars = Arrays.copyOf(chars, Math.max(0, chars.length - amount));
    }

    @Override
    public void remove() {
        remove(1);
    }

    @Override
    public void undo(int i) {
        for (int a = 0; a < i; a++) {
            if (undoStorage.isEmpty() || undoIndex == undoStorage.size() - 1) {
                return;
            }
            undoIndex++;
            Action act = undoStorage.get(undoIndex);
            if (act.added)
                chars = Arrays.copyOf(chars, chars.length - act.cs.length());
            else {
                isFit(act.cs.length() + chars.length);
                System.arraycopy(act.cs.toString().toCharArray(), 0, chars, chars.length - act.cs.length(), act.cs.length());
            }
        }
    }

    @Override
    public void undo() {
        undo(1);
    }

    @Override
    public void redo(int i) {
        for (int a = 0; a < i; a++) {
            if (undoStorage.isEmpty() || undoIndex < 0)
                return;
            Action act = undoStorage.get(undoIndex);
            undoIndex--;

            if (!act.added)
                chars = Arrays.copyOf(chars, chars.length - act.cs.length());
            else {
                isFit(act.cs.length() + chars.length);
                System.arraycopy(act.cs.toString().toCharArray(), 0, chars, chars.length - act.cs.length(), act.cs.length());
            }

        }
    }

    @Override
    public void redo() {
        redo(1);
    }

    @Override
    public String toString() {
        return String.valueOf(chars);
    }
}
