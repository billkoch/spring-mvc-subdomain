package io.billkoch.springextensions.mvc.bind.annotation;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubdomainExtractorTest {

    private SubdomainExtractor uut;

    @Test
    public void topLevelDomain() {
        assertThat(uut.extract("google.com")).isEqualTo(null);
    }

    @Test
    public void singleLevelSubdomain() {
        assertThat(uut.extract("pixel.google.com")).isEqualTo("pixel");
    }

    @Test
    public void multiLevelSubdomain() {
        assertThat(uut.extract("pixel.phone.google.com")).isEqualTo("pixel.phone");
    }

    @Test
    public void localhost() {
        assertThat(uut.extract("localhost")).isEqualTo(null);
    }

    @Test
    public void privateDomain() {
        assertThat(uut.extract("pixel.some-madeup-domain.com")).isEqualTo("pixel");
    }

    @Before
    public void setUp() throws Exception {
        uut = new SubdomainExtractor();
    }
}
