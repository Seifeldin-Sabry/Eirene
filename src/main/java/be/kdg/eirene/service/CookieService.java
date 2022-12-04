package be.kdg.eirene.service;

import javax.servlet.http.HttpSession;

public interface CookieService {

	boolean cookieInvalid(HttpSession session);

	boolean cookieInvalid(HttpSession session, Long userId);

	void setCookie(HttpSession session, Long userId);

	Long getAttribute(HttpSession session);

	void deleteCookie(HttpSession session);

}
