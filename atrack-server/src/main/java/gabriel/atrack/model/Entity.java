package gabriel.atrack.model;

import gabriel.atrack.activity.model.ActivityTypeDef_.DATA_TYPE;

import javax.persistence.Transient;

/**
 * This interface provides value to relationship to identify entity.
 * 
 */
public interface Entity {

	/**
	 * Returns PK of the entity
	 * 
	 * @return PK
	 */
	Long getEntityID();

	public enum ENTITY_TYPE {
		SUBJECT(100);
		int code;

		ENTITY_TYPE(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		static ENTITY_TYPE getType(int code) {

			ENTITY_TYPE[] s = ENTITY_TYPE.values();
			for (ENTITY_TYPE t : s) {
				if (t.getCode() == code)
					return t;
			}
			return null;

		}
	}

	/**
	 * Should return a static final constant
	 * 
	 * @return
	 */
	@Transient
	ENTITY_TYPE getEntityType();
}
