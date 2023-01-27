package be.kdg.eirene.service;

public interface PaginationService {
	int getPageSize();

	int getPageNumber();

	void setPageNumber(int pageNumber);

	Integer getNumberOfPages();

	void setNumberOfPages(Integer numberOfPages);

	void reset();

	void throwErrorIfPageDoesNotExist(int page);

	int getCorrectPageNumberAfterDelete();
}
