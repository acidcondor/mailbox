package org.benjob.smartmailbox.bo;

import org.benjob.smartmailbox.model.Parcel;

public interface ParcelBo extends BaseBo<Parcel> {
    public Parcel create( Parcel parcel, Long person_id);
}
