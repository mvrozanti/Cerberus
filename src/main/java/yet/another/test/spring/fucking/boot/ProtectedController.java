package yet.another.test.spring.fucking.boot;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import s4desk.operacao.controller.ControllerEmprestimo;
import s4utils.ConfigUtils;
import s4utils.DataBaseUtils;

@RestController
@RequestMapping("${cerberus.route.protected}")
public class ProtectedController {

	private ControllerEmprestimo ce;

	/**
	 * This is an example of some different kinds of granular restriction for
	 * endpoints. You can use the built-in SPEL expressions in @PreAuthorize
	 * such as 'hasRole()' to determine if a user has access. However, if you
	 * require logic beyond the methods Spring provides then you can encapsulate
	 * it in a service and register it as a bean to use it within the annotation
	 * as demonstrated below with 'securityService'.
	 *
	 */
	@RequestMapping(method = RequestMethod.GET)
//  @PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("@securityService.hasProtectedAccess()")
	public ResponseEntity<?> getDaHoney() {
		if (!ConfigUtils.foiLida()) {
			ConfigUtils.readArguments(new String[]{});
			try {
				ConfigUtils.readPropertiesConfiguration("4Desk");
				DataBaseUtils.openConnection();
				ce = new ControllerEmprestimo();
			} catch (Exception ex) {
				Logger.getLogger(ProtectedController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return ResponseEntity.ok(ce.getPossiveisEmpresas());
	}

}
