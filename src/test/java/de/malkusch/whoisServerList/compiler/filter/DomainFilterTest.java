package de.malkusch.whoisServerList.compiler.filter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import de.malkusch.whoisServerList.api.v1.model.DomainList;
import de.malkusch.whoisServerList.api.v1.model.domain.Domain;
import de.malkusch.whoisServerList.compiler.helper.WhoisErrorResponseDetector;
import de.malkusch.whoisServerList.compiler.test.TestUtil;
import de.malkusch.whoisServerList.compiler.test.WhoisApiRule;

@RunWith(Parameterized.class)
public class DomainFilterTest {

    @Rule
    public WhoisApiRule whoisApiRule = new WhoisApiRule();

    @Parameter(0)
    public Domain domain;

    @Parameter(1)
    public Domain expected;

    @Parameter(2)
    public Pattern[] patterns;

    @Parameters
    public static Collection<Object[]> getFilters() {
        Collection<Object[]> cases = new ArrayList<>();

        Pattern[] patterns = new Pattern[] { Pattern.compile(Pattern.quote("not found"), Pattern.CASE_INSENSITIVE) };

        {
            Domain domain = TestUtil.buildDomain("com", "whois.verisign-grs.com", "no match for");
            Domain expected = domain.clone();
            cases.add(new Object[] { domain, expected, patterns });
        }

        {
            Domain domain = TestUtil.buildDomain("de", "whois.nic.de", "no match for");
            Domain expected = TestUtil.buildDomain("de", "whois.nic.de", null);
            cases.add(new Object[] { domain, expected, patterns });
        }

        {
            Domain domain = TestUtil.buildSimpleDomain(" net ");
            Domain expected = TestUtil.buildSimpleDomain("net");
            cases.add(new Object[] { domain, expected, patterns });
        }

        {
            Domain domain = TestUtil.buildDomain("org", "whois.pir.org", null);
            Domain expected = TestUtil.buildDomain("org", "whois.pir.org", "not found");
            cases.add(new Object[] { domain, expected, patterns });
        }

        {
            Domain domain = TestUtil.buildDomain("as", "whois.nic.as", "not found");
            Domain expected = domain.clone();
            cases.add(new Object[] { domain, expected, patterns });
        }
        {
            Domain domain = TestUtil.buildDomain("de", "whois.denic.de", "Status: free");
            Domain expected = domain.clone();
            cases.add(new Object[] { domain, expected, patterns });
        }
        {
            Domain domain = TestUtil.buildDomain("de", "whois.nic.de", "Status: free");
            Domain expected = domain.clone();
            cases.add(new Object[] { domain, expected, patterns });
        }
        {
            Domain domain = TestUtil.buildDomain("org", "whois.pir.org", "not found");
            Domain expected = domain.clone();
            cases.add(new Object[] { domain, expected, patterns });
        }
        {
            Domain domain = TestUtil.buildDomain("السعودية", "whois.nic.net.sa", "No Match");
            Domain expected = domain.clone();
            cases.add(new Object[] { domain, expected, patterns });
        }
        {
            Domain domain = TestUtil.buildDomain("abbott", "whois.afilias-srs.net", "NOT FOUND");
            Domain expected = domain.clone();
            cases.add(new Object[] { domain, expected, patterns });
        }

        return cases;
    }

    @Test
    public void testFilter() throws InterruptedException {
        DomainFilter<Domain> filter = new DomainFilter<>(Arrays.asList(patterns), new WhoisErrorResponseDetector(new DomainList()), whoisApiRule.whoisApi());

        Domain result = filter.filter(domain);
        assertEquals(expected, result);
    }

}
