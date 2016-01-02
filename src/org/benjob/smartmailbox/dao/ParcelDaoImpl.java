package org.benjob.smartmailbox.dao;

import org.benjob.smartmailbox.model.Parcel;

public class ParcelDaoImpl extends BaseDaoImpl<Parcel> implements ParcelDao {

    public ParcelDaoImpl() {
        super( Parcel.class );
    }

}
