package pub.types;

import java.io.Serializable;
 
public class EntityData implements Serializable {
 
	private static final long serialVersionUID = 4607770385333326942L;
	protected EntityStatus status;
	private boolean standalone = false;

	public EntityData() {
		status = EntityStatus.UNCHANGED;
	}

	public EntityStatus getStatus() {
		return status;
	}
	public void setStatus(EntityStatus status) {
		this.status = status;
	}

	public boolean getListable() {
		return status != EntityStatus.DELETED && status != EntityStatus.TEMP;
	}

	public boolean getIsNew() {
		return status == EntityStatus.NEW;
	}

	public boolean isStandalone() {
		return standalone;
	}
	public void setStandalone(boolean standalone) {
		this.standalone = standalone;
	}
	public boolean getIsDirty() {
		return status == EntityStatus.NEW ||
			status == EntityStatus.MODIFIED ||
			status == EntityStatus.DELETED;
	}
}
