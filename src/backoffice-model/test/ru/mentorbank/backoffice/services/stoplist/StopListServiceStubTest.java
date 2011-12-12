package ru.mentorbank.backoffice.services.stoplist;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.mentorbank.backoffice.model.stoplist.JuridicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.PhysicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.StopListInfo;
import ru.mentorbank.backoffice.model.stoplist.StopListStatus;
import ru.mentorbank.backoffice.test.AbstractSpringTest;

public class StopListServiceStubTest extends AbstractSpringTest {

	@Autowired
	private StopListServiceStub stopListService;
	private JuridicalStopListRequest juridicalStopListRequest;
	private PhysicalStopListRequest physicalStopListRequest;

	@Before
	public void setUp() {
		juridicalStopListRequest = new JuridicalStopListRequest();
		physicalStopListRequest = new PhysicalStopListRequest();
	}

	@Test
	public void getJuridicalStopListInfo_OK() {
		// setup SUT
		juridicalStopListRequest.setInn(StopListServiceStub.INN_FOR_OK_STATUS);
		// Call SUT
		StopListInfo info = stopListService
				.getJuridicalStopListInfo(juridicalStopListRequest);
		// Validate SUT
		assertNotNull("���������� ������ ���� ���������", info);
		assertEquals(StopListStatus.OK, info.getStatus());
	}

	@Test
	public void getJuridicalStopListInfo_STOP() {
		juridicalStopListRequest.setInn(StopListServiceStub.INN_FOR_STOP_STATUS);
		StopListInfo info = stopListService
				.getJuridicalStopListInfo(juridicalStopListRequest);
		assertNotNull("���������� ������ ���� ���������", info);
		assertEquals(StopListStatus.STOP, info.getStatus());
	}
	
	@Test
	public void getPhysicalStopListInfo_OK() {
		physicalStopListRequest.setDocumentNumber(StopListServiceStub.DOC_NUMBER_FOR_OK_STATUS);
		physicalStopListRequest.setDocumentSeries(StopListServiceStub.DOC_SERIES_FOR_OK_STATUS);
		
		StopListInfo info = stopListService.getPhysicalStopListInfo(physicalStopListRequest);
		assertNotNull("���������� ������ ���� ���������", info);
		assertEquals(StopListStatus.OK, info.getStatus());
	}
	
	@Test
	public void getPhysicalStopListInfo_STOP() {
		physicalStopListRequest.setDocumentNumber(StopListServiceStub.DOC_NUMBER_FOR_STOP_STATUS);
		physicalStopListRequest.setDocumentSeries(StopListServiceStub.DOC_SERIES_FOR_STOP_STATUS);
		
		StopListInfo info = stopListService.getPhysicalStopListInfo(physicalStopListRequest);
		assertNotNull("���������� ������ ���� ���������", info);
		assertEquals(StopListStatus.STOP, info.getStatus());
	}
	

}
