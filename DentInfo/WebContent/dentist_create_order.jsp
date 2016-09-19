<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ page import="model.mainObjects.*, java.util.ArrayList"%>
<%
	String username = (String) request.getSession().getAttribute("logged");
	if (username == null) {
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}
	Dentist dentist = ((Dentist) session.getAttribute("user"));
	ArrayList<Patient> patientList = PatientManager.getInstance().getPatientsByDentist(dentist);
	String firstName = dentist.getFirstName();
	String lastName = dentist.getLastName();
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<%@include file="head.html"%>
<title>Dentist Patients</title>
</head>
<body>

	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					D-r
					<%
					out.print(firstName + " " + lastName);
				%>
					patients list
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<p class="text-muted font-13 m-b-30">
					<a href="dentist_main.jsp"><button type="submit"
							class="btn btn-primary">Dentist main</button></a> <a
						href="dentist_create_patient.jsp"><button type="submit"
							class="btn btn-primary">New patient</button></a> <a
						href="dentist_create_order.jsp"><button type="submit"
							class="btn btn-primary">New order</button></a> <a
						href="dentist_list_patients.jsp"><button type="submit"
							class="btn btn-primary">Patients list</button></a> <a
						href="dentist_view_orders.jsp"><button type="submit"
							class="btn btn-primary">Orders List</button></a> <a href="Logout"><button
							type="submit" class="btn btn-primary">Logout</button></a>
				</p>
			</div>
			<div class="x_content">
				<br>
				<form class="form-horizontal form-label-left" action="RegisterOrder">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Patient</label>
						<div class="col-md-9 col-sm-9 col-xs-12">
							<select class="form-control">
								<option></option>
								<%
									for (Patient patient : patientList) {
										String fullname = patient.getFirstName() + " " + patient.getLastName();
								%>
								<option value="<%out.print(patient.getUser_id());%>">
									<%
										out.print(fullname);
									%>
								</option>
								<%
									}
								%>
							</select>
						</div>
					</div>







					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Default
							Input</label>
						<div class="col-md-9 col-sm-9 col-xs-12">
							<input type="text" class="form-control"
								placeholder="Default Input">
						</div>
					</div>


					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Date
							Of Birth <span class="required">*</span>
						</label>
						<div class="col-md-9 col-sm-9 col-xs-12">
							<textarea class="form-control" rows="3"
								placeholder="rows=&quot;3&quot;"></textarea>
						</div>
					</div>



					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Select
							Custom</label>
						<div class="col-md-9 col-sm-9 col-xs-12">
							<select
								class="select2_single form-control select2-hidden-accessible"
								tabindex="-1" aria-hidden="true">
								<option></option>
								<option value="AK">Alaska</option>
								<option value="HI">Hawaii</option>
								<option value="CA">California</option>
								<option value="NV">Nevada</option>
								<option value="OR">Oregon</option>
								<option value="WA">Washington</option>
								<option value="AZ">Arizona</option>
								<option value="CO">Colorado</option>
								<option value="ID">Idaho</option>
								<option value="MT">Montana</option>
								<option value="NE">Nebraska</option>
								<option value="NM">New Mexico</option>
								<option value="ND">North Dakota</option>
								<option value="UT">Utah</option>
								<option value="WY">Wyoming</option>
								<option value="AR">Arkansas</option>
								<option value="IL">Illinois</option>
								<option value="IA">Iowa</option>
								<option value="KS">Kansas</option>
								<option value="KY">Kentucky</option>
								<option value="LA">Louisiana</option>
								<option value="MN">Minnesota</option>
								<option value="MS">Mississippi</option>
								<option value="MO">Missouri</option>
								<option value="OK">Oklahoma</option>
								<option value="SD">South Dakota</option>
								<option value="TX">Texas</option>
							</select><span
								class="select2 select2-container select2-container--default"
								dir="ltr" style="width: 407px;"><span class="selection"><span
									class="select2-selection select2-selection--single"
									role="combobox" aria-haspopup="true" aria-expanded="false"
									tabindex="-1" aria-labelledby="select2-errn-container"><span
										class="select2-selection__rendered"
										id="select2-errn-container"><span
											class="select2-selection__placeholder">Select a state</span></span><span
										class="select2-selection__arrow" role="presentation"><b
											role="presentation"></b></span></span></span><span class="dropdown-wrapper"
								aria-hidden="true"></span></span>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 col-sm-3 col-xs-12 control-label">Checkboxes
							and radios <br> <small class="text-navy">Normal
								Bootstrap elements</small>
						</label>

						<div class="col-md-9 col-sm-9 col-xs-12">
							<div class="checkbox">
								<label> <input type="checkbox" value=""> Option
									one. select more than one options
								</label>
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" value=""> Option
									two. select more than one options
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" checked="" value="option1"
									id="optionsRadios1" name="optionsRadios"> Option one.
									only select one option
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" value="option2"
									id="optionsRadios2" name="optionsRadios"> Option two.
									only select one option
								</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 col-sm-3 col-xs-12 control-label">Checkboxes
							and radios <br> <small class="text-navy">Normal
								Bootstrap elements</small>
						</label>

						<div class="col-md-9 col-sm-9 col-xs-12">
							<div class="checkbox">
								<label>
									<div class="icheckbox_flat-green checked"
										style="position: relative;">
										<input type="checkbox" class="flat" checked="checked"
											style="position: absolute; opacity: 0;">
										<ins class="iCheck-helper"
											style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
									</div> Checked
								</label>
							</div>
							<div class="checkbox">
								<label>
									<div class="icheckbox_flat-green" style="position: relative;">
										<input type="checkbox" class="flat"
											style="position: absolute; opacity: 0;">
										<ins class="iCheck-helper"
											style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
									</div> Unchecked
								</label>
							</div>
							<div class="checkbox">
								<label>
									<div class="icheckbox_flat-green disabled"
										style="position: relative;">
										<input type="checkbox" class="flat" disabled="disabled"
											style="position: absolute; opacity: 0;">
										<ins class="iCheck-helper"
											style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
									</div> Disabled
								</label>
							</div>
							<div class="checkbox">
								<label>
									<div class="icheckbox_flat-green checked disabled"
										style="position: relative;">
										<input type="checkbox" class="flat" disabled="disabled"
											checked="checked" style="position: absolute; opacity: 0;">
										<ins class="iCheck-helper"
											style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
									</div> Disabled &amp; checked
								</label>
							</div>
							<div class="radio">
								<label>
									<div class="iradio_flat-green checked"
										style="position: relative;">
										<input type="radio" class="flat" checked="" name="iCheck"
											style="position: absolute; opacity: 0;">
										<ins class="iCheck-helper"
											style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
									</div> Checked
								</label>
							</div>
							<div class="radio">
								<label>
									<div class="iradio_flat-green" style="position: relative;">
										<input type="radio" class="flat" name="iCheck"
											style="position: absolute; opacity: 0;">
										<ins class="iCheck-helper"
											style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
									</div> Unchecked
								</label>
							</div>
							<div class="radio">
								<label>
									<div class="iradio_flat-green disabled"
										style="position: relative;">
										<input type="radio" class="flat" name="iCheck"
											disabled="disabled" style="position: absolute; opacity: 0;">
										<ins class="iCheck-helper"
											style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
									</div> Disabled
								</label>
							</div>
							<div class="radio">
								<label>
									<div class="iradio_flat-green checked disabled"
										style="position: relative;">
										<input type="radio" class="flat" name="iCheck3"
											disabled="disabled" checked=""
											style="position: absolute; opacity: 0;">
										<ins class="iCheck-helper"
											style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
									</div> Disabled &amp; Checked
								</label>
							</div>
						</div>
					</div>



					<div class="ln_solid"></div>
					<div class="form-group">
						<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
							<button type="reset" class="btn btn-primary">Cancel</button>
							<button type="submit" class="btn btn-success">Send</button>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
	</div>
	<%@include file="bottom_libs.html"%>

</body>
</html>