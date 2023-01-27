package be.kdg.eirene.service;

import be.kdg.eirene.exceptions.PageNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SessionPaginationService implements PaginationService {
	private final int PAGE_SIZE = 10;
	private int pageNumber = 0;
	private Integer numberOfPages;

	@Override
	public int getPageSize() {
		return PAGE_SIZE;
	}

	@Override
	public int getPageNumber() {
		return pageNumber + 1;
	}

	@Override
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber - 1;
	}

	@Override
	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	@Override
	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	@Override
	public void reset() {
		pageNumber = 0;
		numberOfPages = null;
	}

	@Override
	public void throwErrorIfPageDoesNotExist(int page) {
		if (page == 1 || page > 0 && page <= numberOfPages) return;
		throw new PageNotFoundException("Page not found");
	}


	@Override
	public int getCorrectPageNumberAfterDelete() {
		if (getPageNumber() > getNumberOfPages()) {
			return getNumberOfPages() == 0 ? 1 : getNumberOfPages();
		}
		return getPageNumber();
	}
}
