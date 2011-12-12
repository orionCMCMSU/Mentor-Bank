package ru.mentorbank.backoffice.services.moneytransfer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.mentorbank.backoffice.dao.stub.OperationDaoStub;
import ru.mentorbank.backoffice.model.stoplist.JuridicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.PhysicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.StopListInfo;
import ru.mentorbank.backoffice.model.stoplist.StopListStatus;
import ru.mentorbank.backoffice.model.transfer.AccountInfo;
import ru.mentorbank.backoffice.model.transfer.JuridicalAccountInfo;
import ru.mentorbank.backoffice.model.transfer.PhysicalAccountInfo;
import ru.mentorbank.backoffice.model.transfer.TransferRequest;
import ru.mentorbank.backoffice.services.accounts.AccountService;
import ru.mentorbank.backoffice.services.accounts.AccountServiceBean;
import ru.mentorbank.backoffice.services.moneytransfer.exceptions.TransferException;
import ru.mentorbank.backoffice.services.stoplist.StopListService;
import ru.mentorbank.backoffice.services.stoplist.StopListServiceStub;
import ru.mentorbank.backoffice.test.AbstractSpringTest;

public class MoneyTransferServiceTest extends AbstractSpringTest {

	@Autowired
	private MoneyTransferService moneyTransferService;
	private MoneyTransferServiceBean moneyTransferServiceBean;
	private StopListService mockedStopListService;
	private AccountService mockedAccountService;
	private OperationDaoStub operationDaoStub;
	private PhysicalAccountInfo srcAccount;
	private JuridicalAccountInfo dstAccount;
	private TransferRequest request;

	
	@Before
	public void setUp() {
		
		mockedStopListService = mock(StopListServiceStub.class);
		mockedAccountService = mock(AccountServiceBean.class);
		
		srcAccount = new PhysicalAccountInfo();
		dstAccount = new JuridicalAccountInfo();
		moneyTransferServiceBean = (MoneyTransferServiceBean)moneyTransferService;
		operationDaoStub = new OperationDaoStub();
		
		moneyTransferServiceBean.setOperationDao(operationDaoStub);
		moneyTransferServiceBean.setAccountService(mockedAccountService);
		moneyTransferServiceBean.setStopListService(mockedStopListService);

		request = new TransferRequest();

		request.setSrcAccount(srcAccount);
		request.setDstAccount(dstAccount);
	}

	@Test
	public void transfer() throws TransferException {
//		fail("not implemented yet");
		// TODO: [done] Необходимо протестировать, что для хорошего перевода всё
		// работает и вызываются все необходимые методы сервисов
		// Далее следует закоментированная закотовка
		// StopListService mockedStopListService =
		// mock(StopListServiceStub.class);
		// AccountService mockedAccountService = mock(AccountServiceBean.class);
		//
		// moneyTransferService.transfer(null);
		//
		// verify(mockedStopListService).getJuridicalStopListInfo(null);
		// verify(mockedAccountService).verifyBalance(null);
		
		
		StopListInfo okStopListinfo = new StopListInfo();
		okStopListinfo.setStatus(StopListStatus.OK);
		
		when(mockedAccountService.verifyBalance(srcAccount)).thenReturn(true);
		
		when(mockedStopListService.getPhysicalStopListInfo(any(PhysicalStopListRequest.class))).thenReturn(okStopListinfo);
		when(mockedStopListService.getJuridicalStopListInfo(any(JuridicalStopListRequest.class))).thenReturn(okStopListinfo);
		
		moneyTransferService.transfer(request);
		
		verify(mockedStopListService).getJuridicalStopListInfo(any(JuridicalStopListRequest.class));
		verify(mockedAccountService).verifyBalance(any(AccountInfo.class));
		
		assertTrue(operationDaoStub.isSaved()) ;
	}
}
