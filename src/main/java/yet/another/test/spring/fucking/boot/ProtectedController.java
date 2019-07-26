package yet.another.test.spring.fucking.boot;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import s4desk.operacao.controller.ControllerEmprestimo;
import s4desk.operacao.model.Operacao;
import s4desk.operacao.model.dao.OperacaoDAO;
import s4utils.ConfigUtils;
import s4utils.DataBaseUtils;

@RestController
@RequestMapping("${cerberus.route.protected}")
public class ProtectedController {

	private ControllerEmprestimo ce;

	private void init(){
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
	}

	/**
	 * This is an example of some different kinds of granular restriction for
	 * endpoints. You can use the built-in SPEL expressions in @PreAuthorize
	 * such as 'hasRole()' to determine if a user has access. However, if you
	 * require logic beyond the methods Spring provides then you can encapsulate
	 * it in a service and register it as a bean to use it within the annotation
	 * as demonstrated below with 'securityService'.
	 *
	 */
	@RequestMapping(value = "/possiveis_empresas", method = RequestMethod.GET)
	@PreAuthorize("@securityService.hasProtectedAccess()")
	public ResponseEntity<?> getDaHoney() {
		init();
		return ResponseEntity.ok(ce.getPossiveisEmpresas());
	}
	@RequestMapping(value = "/operacoes/{idt}", method = RequestMethod.GET)
	@PreAuthorize("@securityService.hasProtectedAccess()")
	public ResponseEntity<?> getOperacao(@PathVariable("idt") long idt) {
		init();
		Operacao o = new Operacao(idt);
		try {
			new OperacaoDAO().getUnique(o);
		} catch (Exception ex) {
			Logger.getLogger(ProtectedController.class.getName()).log(Level.SEVERE, null, ex);
		}
		ResponseEntity<?> re = ResponseEntity.ok(o);
		return re;
	}

}
