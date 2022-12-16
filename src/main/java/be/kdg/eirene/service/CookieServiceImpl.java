package be.kdg.eirene.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Getter
@Setter
public class CookieServiceImpl implements CookieService {

	public final String COOKIE_NAME = "user_id";
	private Integer totalPages;
	private int currentPage;

	@Override
	public boolean cookieInvalid(HttpSession session) {
		return session.getAttribute(COOKIE_NAME) == null;
	}

	@Override
	public boolean cookieInvalid(HttpSession session, Long userId) {
		return cookieInvalid(session) || !session.getAttribute(COOKIE_NAME).equals(userId);
	}

	@Override
	public void setCookie(HttpSession session, Long userId) {
		session.setAttribute(COOKIE_NAME, userId);
	}

	@Override
	public Long getAttribute(HttpSession session) {
		return session.getAttribute(COOKIE_NAME) == null ? null : (Long) session.getAttribute(COOKIE_NAME);
	}

	@Override
	public void removeCookie(HttpSession session) {
		resetPages();
		session.removeAttribute(COOKIE_NAME);
		session.invalidate();
	}

	@Override
	public void resetPages() {
		totalPages = null;
		currentPage = 0;
	}

}