package mainObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class DentalLaboratory {
	private String bulstat;
	private String name;
	private Address address;
	private HashMap<String, Manager> managerList;
	private HashMap<String, Dentist> dentistList;
	private HashMap<String, Service> serviceList;
	private HashMap<Dentist, HashMap<Patient, ArrayList<Order>>> orderList;

}

/*
 * ��� ������� �������� ��������� - ��� �������� ������ ��� ����������, � �����
 * ������ ������ � ������, ����� ��������. ������ � ������� ������
 * ��������������-����������
 */
