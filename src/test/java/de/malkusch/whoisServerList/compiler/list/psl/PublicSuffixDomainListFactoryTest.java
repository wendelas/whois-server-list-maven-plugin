package de.malkusch.whoisServerList.compiler.list.psl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.malkusch.whoisServerList.api.v1.model.Source;
import de.malkusch.whoisServerList.api.v1.model.domain.TopLevelDomain;
import de.malkusch.whoisServerList.compiler.list.exception.BuildListException;
import de.malkusch.whoisServerList.compiler.test.TestUtil;

public class PublicSuffixDomainListFactoryTest {

    @Test
    public void testBuildList()
            throws BuildListException, InterruptedException, IOException {

        PublicSuffixDomainListFactory factory
            = new PublicSuffixDomainListFactory();

        Map<String, TopLevelDomain> topLevelDomains = new HashMap<>();
        for (TopLevelDomain tld : factory.buildList().getDomains()) {
            assertFalse(topLevelDomains.containsKey(tld.getName()));
            topLevelDomains.put(tld.getName(), tld);

        }

        TopLevelDomain de = topLevelDomains.get("de");
        assertEquals("de", de.getName());
        assertEquals("DE", de.getCountryCode());

        TopLevelDomain uk = topLevelDomains.get("uk");
        assertFalse(uk.getDomains().isEmpty());
        assertTrue(uk.getDomains().contains(
                TestUtil.buildSimpleDomain("co.uk", Source.PSL)));
    }

}
