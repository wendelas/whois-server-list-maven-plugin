package de.malkusch.whoisServerList.compiler.helper.converter;

import net.jcip.annotations.Immutable;
import de.malkusch.whoisServerList.compiler.model.WhoisServer;

/**
 * Converts a whois server to its host name.
 *
 * @author markus@malkusch.de
 * @see <a href="bitcoin:1335STSwu9hST4vcMRppEPgENMHD2r1REK">Donations</a>
 */
@Immutable
public final class WhoisServerToHostConverter
        implements Converter<WhoisServer, String> {

    @Override
    public String convert(final WhoisServer value) {
        if (value == null) {
            return null;

        }
        return value.getHost();
    }

}