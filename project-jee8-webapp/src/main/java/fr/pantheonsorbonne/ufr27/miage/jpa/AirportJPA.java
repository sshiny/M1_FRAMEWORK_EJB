package fr.pantheonsorbonne.ufr27.miage.jpa;


	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;

	@Entity
	public class AirportJPA {
		@Override
		public String toString() {
			return "code=" + code + ", street=" + street;
		}
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		int code;

		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getSreet() {
			return street;
		}
		public void setStreetName(String street) {
			this.street = street;
		}
		
		String street;
}


