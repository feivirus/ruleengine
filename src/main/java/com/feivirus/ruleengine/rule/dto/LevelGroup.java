package  com.feivirus.ruleengine.rule.dto;

/**
 * 
 * @author feivirus
 *
 */
public class LevelGroup {
	//团体id，比如帮派，渠道，
	private Integer id;
	
	private String name;
	
	//类型 1 飞侠的帮派  2.渠道 3.以前喂小保的场景,参考GroupTypeEnum
	private Integer type;
	
	public LevelGroup(Integer id) {
        this.id = id;
    }

	public LevelGroup() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}	
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof LevelGroup){
            LevelGroup other = (LevelGroup) obj;
            return this.getId().equals(other.getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + (this.id == null ? 0 : this.id.hashCode());
        return hashCode;
    }

}
