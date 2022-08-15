package mgs.training.springboot.belajarjdbc.comparator;

import java.util.Comparator;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;

public class JabatanSortDesc implements Comparator<JabatanDto>{

	@Override
	public int compare(JabatanDto o1, JabatanDto o2) {
		return o2.getId().compareTo(o1.getId());
	}

}
