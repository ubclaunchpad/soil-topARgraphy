package com.appit.sophiemberger.create_quiz;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class QuizQuestions {

    //a queue containing the questions for the quiz (FIFO)
    //the first question to be created by the user will be displayed first
   static Queue quizQuestions = new Queue() {

        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean offer(Object o) {
            return false;
        }

        @Override
        public Object remove() {
            return null;
        }

        @Override
        public Object poll() {
            return null;
        }

        @Override
        public Object element() {
            return null;
        }

        @Override
        public Object peek() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public Object[] toArray(@NonNull Object[] a) {
            return new Object[0];
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };
}
