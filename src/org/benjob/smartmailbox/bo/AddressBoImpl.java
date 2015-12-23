package org.benjob.smartmailbox.bo;

import org.benjob.smartmailbox.dao.AddressDao;
import org.benjob.smartmailbox.model.Address;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AddressBoImpl extends BaseBoImpl<Address, AddressDao> implements AddressBo {

    public AddressBoImpl() {
        // TODO Auto-generated constructor stub
    }

}
