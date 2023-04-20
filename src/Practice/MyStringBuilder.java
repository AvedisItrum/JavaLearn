package Practice;

import java.util.*;
import java.util.stream.IntStream;

public class MyStringBuilder implements Appendable, CharSequence, Undoable, Removable {


    static class Action {
        public CharSequence cs;
        public Boolean added;

        public Action(CharSequence cs, boolean added) {
            this.cs = cs;
            this.added = added;
        }
    }

    char[] chars;
    int UndoCapacity = 15;
    Stack<Action> undoStorage;
    Stack<Action> reUndoStorage;

    public MyStringBuilder(String str) {
        chars = str.toCharArray();
        undoStorage = new Stack<>();
        reUndoStorage = new Stack<>();
    }

    public void UndoAdd(CharSequence cs, Boolean added) {
        undoStorage.push(new Action(cs, added));
        reUndoStorage.clear();
        if (undoStorage.size() > UndoCapacity)
            undoStorage.remove(undoStorage.size());
    }

    public void Fitnes(int length) {
        if (length > chars.length)
            chars = Arrays.copyOf(chars, length);
    }

    @Override
    public Appendable append(CharSequence csq) {
        UndoAdd(csq, true);
        Fitnes(csq.length() + chars.length);
        System.arraycopy(csq.toString().toCharArray(), 0, chars, chars.length - csq.length(), csq.length());
        return this;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) {
        UndoAdd(csq.subSequence(start, end), true);
        Fitnes(csq.length() + chars.length);
        System.arraycopy(csq.toString().toCharArray(), start, chars, chars.length - csq.length(), end - start);
        return this;
    }

    @Override
    public Appendable append(char c) {
        UndoAdd(String.valueOf(c), true);
        Fitnes(chars.length + 1);
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
    public void Remove(int i) {
        UndoAdd(this.subSequence(chars.length - i, chars.length), false);
        chars = Arrays.copyOf(chars, chars.length - i);
    }

    @Override
    public void Remove() {
        Remove(1);
    }

    @Override
    public void Undo(int i) {
        for (int a = 0; a < i; a++) {
            if (undoStorage.isEmpty())
                return;
            Action act = undoStorage.pop();
            reUndoStorage.push(act);
            if (act.added)
                chars = Arrays.copyOf(chars, chars.length - act.cs.length());
            else {
                Fitnes(act.cs.length() + chars.length);
                System.arraycopy(act.cs.toString().toCharArray(), 0, chars, chars.length - act.cs.length(), act.cs.length());
            }


        }
    }

    @Override
    public void Undo() {
        Undo(1);
    }

    @Override
    public void ReUndo(int i) {
        for (int a = 0; a < i; a++) {
            if (reUndoStorage.isEmpty())
                return;
            Action act = reUndoStorage.pop();
            undoStorage.push(act);
            if (!act.added)
                chars = Arrays.copyOf(chars, chars.length - act.cs.length());
            else {
                Fitnes(act.cs.length() + chars.length);
                System.arraycopy(act.cs.toString().toCharArray(), 0, chars, chars.length - act.cs.length(), act.cs.length());
            }

        }
    }

    @Override
    public void ReUndo() {
        ReUndo(1);
    }

    @Override
    public String toString(){
       return String.valueOf(chars);
    }
}
