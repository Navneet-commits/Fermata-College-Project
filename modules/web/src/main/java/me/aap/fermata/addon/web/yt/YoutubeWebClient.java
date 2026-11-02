package me.aap.fermata.addon.web.yt;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import java.net.URI;
import java.util.Locale;

import me.aap.fermata.addon.web.FermataWebClient;

/**
 * @author Andrey Pavlenko
 */
public class YoutubeWebClient extends FermataWebClient {

	@Override
	public boolean shouldOverrideUrlLoading(@NonNull WebView view, @NonNull WebResourceRequest request) {
		return request.isForMainFrame() && !isAllowedPage(request.getUrl().toString());
	}

	static boolean isAllowedPage(String url) {
		if (url == null) return false;
		try {
			URI uri = URI.create(url);
			if (!"https".equalsIgnoreCase(uri.getScheme()) || uri.getHost() == null ||
					uri.getRawUserInfo() != null || (uri.getPort() != -1 && uri.getPort() != 443))
				return false;
			return switch (uri.getHost().toLowerCase(Locale.ROOT)) {
				case "youtube.com", "www.youtube.com", "m.youtube.com", "youtu.be",
						"accounts.google.com", "accounts.youtube.com", "consent.youtube.com" -> true;
				default -> false;
			};
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}
}
