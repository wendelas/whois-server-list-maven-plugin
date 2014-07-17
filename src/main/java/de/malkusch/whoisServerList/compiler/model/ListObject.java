package de.malkusch.whoisServerList.compiler.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A object of the list.
 *
 * @author markus@malkusch.de
 * @see <a href="bitcoin:1335STSwu9hST4vcMRppEPgENMHD2r1REK">Donations</a>
 */
public abstract class ListObject {

    /**
     * The source of this object.
     */
    private Source source;

    /**
     * Returns the source of this object.
     *
     * @return the source
     */
    public final Source getSource() {
        return source;
    }

    /**
     * Sets the source of this object.
     *
     * @param source  the source
     */
    public final void setSource(final Source source) {
        this.source = source;
    }

    @Override
    public final boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
