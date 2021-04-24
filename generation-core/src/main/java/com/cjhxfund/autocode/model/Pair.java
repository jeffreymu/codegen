package com.cjhxfund.autocode.model;

import com.google.errorprone.annotations.Immutable;

import java.io.Serializable;

@Immutable
public class Pair<T1, T2> implements Serializable {
    private static final long serialVersionUID = -1415218564364396395L;

    /**
     * Create a new Pair instance.
     *
     * @param inFirst  a <code>T1</code> value or null
     * @param inSecond a <code>T2</code> value or null
     */
    public Pair(T1 inFirst, T2 inSecond) {
        firstMember = inFirst;
        secondMember = inSecond;
    }

    public Pair() {
        firstMember = null;
        secondMember = null;
    }

    /**
     * Get the firstMember value.
     *
     * @return a <code>T1</code> value
     */
    public T1 getFirstMember() {
        return firstMember;
    }

    public void setFirstMember(T1 t1) {
        firstMember = t1;
    }

    public void setSecondMember(T2 t2) {
        secondMember = t2;
    }

    /**
     * Get the secondMember value.
     *
     * @return a <code>T2</code> value
     */
    public T2 getSecondMember() {
        return secondMember;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s - %s", //$NON-NLS-1$
                firstMember, secondMember);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstMember == null) ? 0 : firstMember.hashCode());
        result = prime * result + ((secondMember == null) ? 0 : secondMember.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair<?, ?> other = (Pair<?, ?>) obj;
        if (firstMember == null) {
            if (other.firstMember != null)
                return false;
        } else if (!firstMember.equals(other.firstMember))
            return false;
        if (secondMember == null) {
            if (other.secondMember != null)
                return false;
        } else if (!secondMember.equals(other.secondMember))
            return false;
        return true;
    }

    private T1 firstMember;
    private T2 secondMember;
}
