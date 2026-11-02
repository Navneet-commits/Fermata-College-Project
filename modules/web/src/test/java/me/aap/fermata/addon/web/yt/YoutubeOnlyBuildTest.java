package me.aap.fermata.addon.web.yt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.aap.fermata.BuildConfig;

public class YoutubeOnlyBuildTest {
    @Test
    public void onlyYouTubeIsRegistered() {
        assertEquals(1, BuildConfig.ADDONS.length);
        assertEquals("web", BuildConfig.ADDONS[0].moduleName);
        assertEquals(YoutubeAddon.class.getName(), BuildConfig.ADDONS[0].className);
    }

    @Test
    public void sharedClassesLoadWithoutRemovedAddonRegistrations() throws Exception {
        Class.forName("me.aap.fermata.addon.web.WebBrowserAddon");
        Class.forName("me.aap.fermata.addon.web.yt.YoutubeAddon");
        Class.forName("me.aap.fermata.addon.SubGenAddon");
        Class.forName("me.aap.fermata.addon.TranslateAddon");
    }

    @Test
    public void navigationKeepsYouTubeAndAccountFlowsWithoutTheRemovedBrowser() {
        assertTrue(YoutubeWebClient.isAllowedPage("https://m.youtube.com/watch?v=abc"));
        assertTrue(YoutubeWebClient.isAllowedPage("https://youtu.be/abc"));
        assertTrue(YoutubeWebClient.isAllowedPage("https://accounts.google.com/signin"));
        assertTrue(YoutubeWebClient.isAllowedPage("https://consent.youtube.com/"));
        for (String url : new String[] {null, "not a url", "http://youtube.com/",
                "https://evilyoutube.com/", "https://youtube.com.evil.test/",
                "https://user@youtube.com/", "https://youtube.com:8443/",
                "intent://youtube.com/", "file:///sdcard/video.html"}) {
            assertFalse(String.valueOf(url), YoutubeWebClient.isAllowedPage(url));
        }
    }
}
