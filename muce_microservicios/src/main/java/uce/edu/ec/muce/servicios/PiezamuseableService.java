package uce.edu.ec.muce.servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import uce.edu.ec.muce.intefaces.EstadogeneralbienRepositorio;
import uce.edu.ec.muce.intefaces.PiezaarqueologicadetalleRepositorio;
import uce.edu.ec.muce.intefaces.PiezabotanicadetalleRepositorio;
import uce.edu.ec.muce.intefaces.PiezaentomologicadetalleRepositorio;
import uce.edu.ec.muce.intefaces.PiezafotograficadetalleRepositorio;
import uce.edu.ec.muce.intefaces.PiezageologicadetalleRepositorio;
import uce.edu.ec.muce.intefaces.PiezainstrumentaldetalleRepositorio;
import uce.edu.ec.muce.intefaces.PiezamuseableRepositorio;
import uce.edu.ec.muce.intefaces.PiezamuseablecatalogoRepositorio;
import uce.edu.ec.muce.intefaces.PiezapaleontologicadetalleRepositorio;
import uce.edu.ec.muce.intefaces.PiezazoologicadetalleRepositorio;
import uce.edu.ec.muce.modelos.Estadogeneralbien;
import uce.edu.ec.muce.modelos.Piezaarqueologicadetalle;
import uce.edu.ec.muce.modelos.Piezabotanicadetalle;
import uce.edu.ec.muce.modelos.Piezaentomologicadetalle;
import uce.edu.ec.muce.modelos.Piezafotograficadetalle;
import uce.edu.ec.muce.modelos.Piezageologicadetalle;
import uce.edu.ec.muce.modelos.Piezainstrumentaldetalle;
import uce.edu.ec.muce.modelos.Piezamuseable;
import uce.edu.ec.muce.modelos.Piezamuseablecatalogo;
import uce.edu.ec.muce.modelos.Piezapaleontologicadetalle;
import uce.edu.ec.muce.modelos.Piezazoologicadetalle;
import uce.edu.ec.muce.modelos.filtros.PiezaDetalle;

@Controller
@RequestMapping("/piezaMuseable")
public class PiezamuseableService extends AbstracService<PiezamuseableRepositorio, Piezamuseable> {
	
	//Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "F://fotos_museo//";
	private static String FOTO_UNO = "_FOTO_UNO_";
	private static String FOTO_DOS = "_FOTO_DOS_";
	private static String FOTO_TRES = "_FOTO_TRES_";
	private static String FOTO_CUATRO = "_FOTO_CUATRO_";
	private static String UPLOADED_FOLDER_ARQUEOLOGIA = UPLOADED_FOLDER+"arqueologia//";
	private static String YACIMIENTO = "_YACIMIENTO_";
	private static String YACIMIENTO_PLANO = "_YACIMIENTO_PLANO_";
	private static String UPLOADED_FOLDER_ENTOMOLOGIA = UPLOADED_FOLDER+"entomologia//";
	private static String ECOSISTEMA="_ECOSISTEMA_";
	private static String CARTOGRAFIA="_CARTOGRAFIA_";
	private static String UPLOADED_FOLDER_INSTRUMENTAL = UPLOADED_FOLDER+"instrumental//";
	private static String REGISTROS="_REGISTRO_";
	private static String PLANOS="_PLANOS_";
	private static String INSTRUCTIVOS="_INSTRUCTIVOS_";
	


	@Autowired
	private EstadogeneralbienRepositorio estadogeneral;

	@Autowired
	private PiezamuseablecatalogoRepositorio piezacatalogo;

	@Autowired
	private PiezaarqueologicadetalleRepositorio arqueologica;

	@Autowired
	private PiezabotanicadetalleRepositorio botanica;

	@Autowired
	private PiezaentomologicadetalleRepositorio entomologica;

	@Autowired
	private PiezafotograficadetalleRepositorio fotografica;

	@Autowired
	private PiezageologicadetalleRepositorio geologica;

	@Autowired
	private PiezainstrumentaldetalleRepositorio instrumental;

	@Autowired
	private PiezapaleontologicadetalleRepositorio peleontologica;

	@Autowired
	private PiezazoologicadetalleRepositorio zoologica;

	@GetMapping("/item/{id}")
	@ResponseBody
	public CompletableFuture<List<Piezamuseable>> findByPadreId(@PathVariable("id") Long id) {

		return CompletableFuture.completedFuture(repo.findByItemId(id));
	}

	@RequestMapping(value = "/detalle", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public PiezaDetalle handleFileUpload(@RequestParam("tipo") int tipo,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("detalle") String detalleStr,
			@RequestParam(value = "estadoGeneral", required = false) String estadoGeneral,
			@RequestParam(value = "catalogosDetalle", required = false) String catalogosDetalle) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		PiezaDetalle detalle = mapper.readValue(detalleStr, PiezaDetalle.class);
		System.out.println(detalleStr);
		PiezaDetalle detalleGuardado = new PiezaDetalle();
		Piezamuseable pm = null;

		switch (tipo) {

		case 1:

			detalle.getPiezaarqueologicadetalle()
					.setPiezamuseableid(setearFotos(file, detalle.getPiezaarqueologicadetalle().getPiezamuseableid()));
			Piezaarqueologicadetalle pa = arqueologica.save(detalle.getPiezaarqueologicadetalle());
			pm = pa.getPiezamuseableid();
			break;
		case 2:
			detalle.getPiezabotanicadetalle()
					.setPiezamuseableid(setearFotos(file, detalle.getPiezabotanicadetalle().getPiezamuseableid()));

			Piezabotanicadetalle pb = botanica.save(detalle.getPiezabotanicadetalle());
			pm = pb.getPiezamuseableid();
			break;
		// entomologia
		case 3:

			detalle.getPiezaentomologicadetalle()
					.setPiezamuseableid(setearFotos(file, detalle.getPiezaentomologicadetalle().getPiezamuseableid()));
			Piezaentomologicadetalle pe = entomologica.save(detalle.getPiezaentomologicadetalle());
			pm = pe.getPiezamuseableid();
			break;

		case 5:

			detalle.getPiezafotograficadetalle()
					.setPiezamuseableid(setearFotos(file, detalle.getPiezafotograficadetalle().getPiezamuseableid()));


			Piezafotograficadetalle pf = fotografica.save(detalle.getPiezafotograficadetalle());
			pm = pf.getPiezamuseableid();
			break;
		case 6:

			detalle.getPiezainstrumentaldetalle()
					.setPiezamuseableid(setearFotos(file, detalle.getPiezainstrumentaldetalle().getPiezamuseableid()));

			Piezainstrumentaldetalle pi = instrumental.save(detalle.getPiezainstrumentaldetalle());
			pm = pi.getPiezamuseableid();
			break;

		case 7:
			// geologia
			detalle.getPiezageologicadetalle()
					.setPiezamuseableid(setearFotos(file, detalle.getPiezageologicadetalle().getPiezamuseableid()));
			Piezageologicadetalle pg = geologica.save(detalle.getPiezageologicadetalle());
			pm = pg.getPiezamuseableid();
			break;

		case 8:
			// paleontologia
			detalle.getPiezapaleontologicadetalle().setPiezamuseableid(
					setearFotos(file, detalle.getPiezapaleontologicadetalle().getPiezamuseableid()));
			Piezapaleontologicadetalle pp = peleontologica.save(detalle.getPiezapaleontologicadetalle());
			pm = pp.getPiezamuseableid();
			break;
		case 9:
			// zoologia
			detalle.getPiezazoologicadetalle()
					.setPiezamuseableid(setearFotos(file, detalle.getPiezazoologicadetalle().getPiezamuseableid()));
			Piezazoologicadetalle pz = zoologica.save(detalle.getPiezazoologicadetalle());
			pm = pz.getPiezamuseableid();
			break;

		default:
			break;
		}

		if (estadoGeneral != null && !estadoGeneral.isEmpty() && !estadoGeneral.equals("null")) {
			estadogeneral.borrarEstadosByPiezaMuseable(pm.getPiezamuseableid());
			Estadogeneralbien[] estados = mapper.readValue(estadoGeneral, Estadogeneralbien[].class);
			for (Estadogeneralbien estadogeneralbien : estados) {
				estadogeneralbien.getPiezaCatalogoPk().setPiezamuseableid(pm.getPiezamuseableid());
				estadogeneral.save(estadogeneralbien);
			}

		} else {
			estadogeneral.borrarEstadosByPiezaMuseable(pm.getPiezamuseableid());
		}

		if (catalogosDetalle != null && !catalogosDetalle.isEmpty() && !catalogosDetalle.equals("null")) {
			piezacatalogo.borrarCatalogosByPiezaMuseable(pm.getPiezamuseableid());
			Piezamuseablecatalogo[] estados = mapper.readValue(catalogosDetalle, Piezamuseablecatalogo[].class);
			for (Piezamuseablecatalogo piezamuseablecatalogo : estados) {
				piezamuseablecatalogo.getPiezacatalogoPk().setPiezamuseableid(pm.getPiezamuseableid());
				piezacatalogo.save(piezamuseablecatalogo);
			}

		} else {
			piezacatalogo.borrarCatalogosByPiezaMuseable(pm.getPiezamuseableid());
		}

		return detalleGuardado;
	}

	private Piezamuseable setearFotos(MultipartFile file, Piezamuseable detalle) throws IOException {
		if(detalle.getPiezamuseableid()!=null) {
			Piezamuseable fotos = repo.getOne(detalle.getPiezamuseableid());
			detalle.setFotografia(fotos.getFotografia());
			detalle.setFotografiados(fotos.getFotografiados());
			detalle.setFotografiatres(fotos.getFotografiatres());
			detalle.setFotografiacuatro(fotos.getFotografiacuatro());
			
		}

		if (file!=null && detalle.getFotografia()!=null) {
			File fileBorrar = new File(detalle.getFotografia());
        	
    		if(fileBorrar.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}
		}
		if(file!=null) {
			byte[] bytes = file.getBytes();
			String ruta = UPLOADED_FOLDER +String.valueOf(detalle.getItemid().getItemid())+ FOTO_UNO+file.getOriginalFilename();
			Path path = Paths.get(ruta);
			Files.write(path, bytes);
			detalle.setFotografia(ruta);
		}
		
		return detalle;

	}

	@RequestMapping(value = "/catalogacion", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public PiezaDetalle catalogacion(@RequestParam("tipo") int tipo, @RequestParam("detalle") String detalleStr,
			@RequestParam(value = "catalogosDetalle", required = false) String catalogosDetalle) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		PiezaDetalle detalle = mapper.readValue(detalleStr, PiezaDetalle.class);
		PiezaDetalle detalleGuardado = new PiezaDetalle();
		Piezamuseable pm = null;
		switch (tipo) {

		case 1:
			detalle.getPiezaarqueologicadetalle()
					.setPiezamuseableid(setearFotos(null, detalle.getPiezaarqueologicadetalle().getPiezamuseableid()));
			Piezaarqueologicadetalle paTmp = arqueologica.getOne(detalle.getPiezaarqueologicadetalle().getDetalleid());
			detalle.getPiezaarqueologicadetalle().setFotografiayacimiento(paTmp.getFotografiayacimiento());
			detalle.getPiezaarqueologicadetalle().setFotoyacimientoplano(paTmp.getFotoyacimientoplano());
			Piezaarqueologicadetalle pa = arqueologica.save(detalle.getPiezaarqueologicadetalle());
			pm = pa.getPiezamuseableid();
			break;
		case 2:
			detalle.getPiezabotanicadetalle()
					.setPiezamuseableid(setearFotos(null, detalle.getPiezabotanicadetalle().getPiezamuseableid()));
			Piezabotanicadetalle pb = botanica.save(detalle.getPiezabotanicadetalle());
			pm = pb.getPiezamuseableid();
			break;

		case 3:

			detalle.getPiezaentomologicadetalle()
					.setPiezamuseableid(setearFotos(null, detalle.getPiezaentomologicadetalle().getPiezamuseableid()));
			Piezaentomologicadetalle peTmp = entomologica.getOne(detalle.getPiezaentomologicadetalle().getDetalleid());
			detalle.getPiezaentomologicadetalle().setFotoecosistema(peTmp.getFotoecosistema());
			detalle.getPiezaentomologicadetalle().setFotocartografia(peTmp.getFotocartografia());
			Piezaentomologicadetalle pe = entomologica.save(detalle.getPiezaentomologicadetalle());
			pm = pe.getPiezamuseableid();
			break;

		case 5:
			detalle.getPiezafotograficadetalle()
					.setPiezamuseableid(setearFotos(null, detalle.getPiezafotograficadetalle().getPiezamuseableid()));
			Piezafotograficadetalle pf = fotografica.save(detalle.getPiezafotograficadetalle());
			pm = pf.getPiezamuseableid();

		case 6:
			detalle.getPiezainstrumentaldetalle()
					.setPiezamuseableid(setearFotos(null, detalle.getPiezainstrumentaldetalle().getPiezamuseableid()));
			Piezainstrumentaldetalle piTmp = instrumental.getOne(detalle.getPiezainstrumentaldetalle().getDetalleid());
			detalle.getPiezainstrumentaldetalle().setFotografoplanos(piTmp.getFotografoplanos());
			detalle.getPiezainstrumentaldetalle().setFotoregistros(piTmp.getFotoregistros());
			detalle.getPiezainstrumentaldetalle().setFotoinstructivo(piTmp.getFotoinstructivo());
			Piezainstrumentaldetalle pi = instrumental.save(detalle.getPiezainstrumentaldetalle());
			pm = pi.getPiezamuseableid();
			break;

		case 7:
			// geologia
			detalle.getPiezageologicadetalle()
					.setPiezamuseableid(setearFotos(null, detalle.getPiezageologicadetalle().getPiezamuseableid()));
			Piezageologicadetalle pg = geologica.save(detalle.getPiezageologicadetalle());
			pm = pg.getPiezamuseableid();
			break;

		case 8:
			// paleontologia
			detalle.getPiezapaleontologicadetalle().setPiezamuseableid(
					setearFotos(null, detalle.getPiezapaleontologicadetalle().getPiezamuseableid()));
			Piezapaleontologicadetalle pp = peleontologica.save(detalle.getPiezapaleontologicadetalle());
			pm = pp.getPiezamuseableid();
			break;
		case 9:
			// zoologia
			detalle.getPiezazoologicadetalle()
					.setPiezamuseableid(setearFotos(null, detalle.getPiezazoologicadetalle().getPiezamuseableid()));
			Piezazoologicadetalle pz = zoologica.save(detalle.getPiezazoologicadetalle());
			pm = pz.getPiezamuseableid();
			break;

		default:
			break;
		}

		if (catalogosDetalle != null && !catalogosDetalle.isEmpty() && !catalogosDetalle.equals("null")) {
			piezacatalogo.borrarCatalogosByPiezaMuseable(pm.getPiezamuseableid());
			Piezamuseablecatalogo[] estados = mapper.readValue(catalogosDetalle, Piezamuseablecatalogo[].class);
			for (Piezamuseablecatalogo piezamuseablecatalogo : estados) {
				piezamuseablecatalogo.getPiezacatalogoPk().setPiezamuseableid(pm.getPiezamuseableid());
				piezacatalogo.save(piezamuseablecatalogo);
			}

		} else {
			piezacatalogo.borrarCatalogosByPiezaMuseable(pm.getPiezamuseableid());
		}

		return detalleGuardado;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value = "/fotografia/{id}", method = RequestMethod.GET)
	public HttpEntity getDocument(@PathVariable Long id) throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		
		Path path = Paths.get(repo.getOne(id).getFotografia());
		byte[] foto = Files.readAllBytes(path);
		return new ResponseEntity(foto, httpHeaders, HttpStatus.OK).ok(foto);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value = "/detalle/fotografia/{id}/{tipo}", method = RequestMethod.GET)
	public HttpEntity getDocumentDetalle(@PathVariable Long id, @PathVariable int tipo) throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);

		byte[] foto = null;
		String ruta = null;

		switch (tipo) {
		// actualizar foto piezaMuseable
		case 1:
			
			ruta=repo.getOne(id).getFotografia();

			break;
		// get foto arqueologia yacimiento
		case 2:

			ruta = arqueologica.getOne(id).getFotografiayacimiento();

			break;
		// get foto arqueologia plana yacimiento
		case 3:

			ruta = arqueologica.getOne(id).getFotoyacimientoplano();

			break;
		// get foto entomologia ecosistema
		case 4:
			ruta = entomologica.getOne(id).getFotoecosistema();
			break;
		// get foto entomologia cartografia
		case 5:
			ruta = entomologica.getOne(id).getFotocartografia();
			break;
		// get foto instrumental registros
		case 6:
			ruta = instrumental.getOne(id).getFotoregistros();
			break;
		// get foto instrumental planos
		case 7:
			ruta = instrumental.getOne(id).getFotoplano();
			break;
		// get foto instrumental instructivos
		case 8:
			ruta = instrumental.getOne(id).getFotoinstructivo();
			break;
		// get pieza museable foto dos
			
		case 9:
			ruta = repo.getOne(id).getFotografiados();
			break;
		// get pieza museable foto tres
		case 10:
			ruta = repo.getOne(id).getFotografiatres();
			break;
		// get pieza museable foto cuatro
		case 11:
			ruta = repo.getOne(id).getFotografiatres();
			break;
		default:
			break;
		}
		if (ruta!=null) {
			Path path = Paths.get(ruta);
			foto = Files.readAllBytes(path);
		}
		
		if (foto == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity(foto, httpHeaders, HttpStatus.OK).ok(foto);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping(value = "/foto/tipo", method = RequestMethod.POST)
	@ResponseBody
	public HttpEntity foto(@RequestParam("tipo") int tipo,
			@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("id") Long id)
			throws IOException {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		String rutaAntiguo=null;
		String rutaNuevo=null;

		switch (tipo) {
		// actualizar foto piezaMuseable
		case 1:
			
			Piezamuseable p = repo.getOne(id);
			rutaAntiguo =p.getFotografia();
			rutaNuevo = UPLOADED_FOLDER +String.valueOf(p.getItemid().getItemid())+ FOTO_UNO+file.getOriginalFilename();
			p.setFotografia(rutaNuevo);
			repo.save(p);
			break;
		// actualizar foto arqueologia yacimiento
		case 2:
			Piezaarqueologicadetalle a = arqueologica.getOne(id);
			rutaAntiguo =a.getFotografiayacimiento();
			rutaNuevo = UPLOADED_FOLDER_ARQUEOLOGIA +String.valueOf(a.getPiezamuseableid().getItemid().getItemid())+ YACIMIENTO+file.getOriginalFilename();
			a.setFotografiayacimiento(rutaNuevo);
			arqueologica.save(a);
			break;
		// actualizar foto arqueologia plana yacimiento
		case 3:
			Piezaarqueologicadetalle a2 = arqueologica.getOne(id);
			rutaAntiguo =a2.getFotoyacimientoplano();
			rutaNuevo = UPLOADED_FOLDER_ARQUEOLOGIA +String.valueOf(a2.getPiezamuseableid().getItemid().getItemid())+ YACIMIENTO_PLANO+file.getOriginalFilename();			
			a2.setFotoyacimientoplano(rutaNuevo);
			arqueologica.save(a2);

			break;
		// actualizar foto entomologia ecosistema
		case 4:
			Piezaentomologicadetalle en = entomologica.getOne(id);
			rutaAntiguo =en.getFotoecosistema();
			rutaNuevo = UPLOADED_FOLDER_ENTOMOLOGIA +String.valueOf(en.getPiezamuseableid().getItemid().getItemid())+ ECOSISTEMA+file.getOriginalFilename();			
			en.setFotoecosistema(rutaNuevo);
			entomologica.save(en);
			break;
		// actualizar foto entomologia cartografia
		case 5:
			Piezaentomologicadetalle en2 = entomologica.getOne(id);
			rutaAntiguo =en2.getFotocartografia();
			rutaNuevo = UPLOADED_FOLDER_ENTOMOLOGIA +String.valueOf(en2.getPiezamuseableid().getItemid().getItemid())+ CARTOGRAFIA+file.getOriginalFilename();			
			en2.setFotocartografia(rutaNuevo);			
			entomologica.save(en2);
			break;
		// get foto instrumental registros
		case 6:
			Piezainstrumentaldetalle i = instrumental.getOne(id);
			rutaAntiguo =i.getFotoregistros();
			rutaNuevo = UPLOADED_FOLDER_INSTRUMENTAL +String.valueOf(i.getPiezamuseableid().getItemid().getItemid())+ REGISTROS+file.getOriginalFilename();			
			i.setFotoregistros(rutaNuevo);	
			
			instrumental.save(i);
			break;
		// get foto instrumental planos
		case 7:
			Piezainstrumentaldetalle i2 = instrumental.getOne(id);
			rutaAntiguo =i2.getFotoplano();
			rutaNuevo = UPLOADED_FOLDER_INSTRUMENTAL +String.valueOf(i2.getPiezamuseableid().getItemid().getItemid())+ PLANOS+file.getOriginalFilename();			
			i2.setFotoplano(rutaNuevo);				
			instrumental.save(i2);
			break;
		// get foto instrumental instructivos
		case 8:
			Piezainstrumentaldetalle i3 = instrumental.getOne(id);
			rutaAntiguo =i3.getFotoinstructivo();
			rutaNuevo = UPLOADED_FOLDER_INSTRUMENTAL +String.valueOf(i3.getPiezamuseableid().getItemid().getItemid())+ INSTRUCTIVOS+file.getOriginalFilename();			
			i3.setFotoinstructivo(rutaNuevo);	
			
			instrumental.save(i3);
			break;
		// fotografia dos pieza museable
		case 9:
			Piezamuseable dos = repo.getOne(id);
			//dos.setFotografiados(file.getBytes());
			rutaAntiguo =dos.getFotografiados();
			rutaNuevo = UPLOADED_FOLDER +String.valueOf(dos.getItemid().getItemid())+ FOTO_DOS+file.getOriginalFilename();
			dos.setFotografiados(rutaNuevo);
			repo.save(dos);

			break;
		// fotografia tres pieza museable
		case 10:
			Piezamuseable tres = repo.getOne(id);
			//tres.setFotografiatres(file.getBytes());
			rutaAntiguo =tres.getFotografiatres();
			rutaNuevo = UPLOADED_FOLDER +String.valueOf(tres.getItemid().getItemid())+ FOTO_TRES+file.getOriginalFilename();
			tres.setFotografiatres(rutaNuevo);
			repo.save(tres);
			break;
		// fotografia cuatro pieza museable
		case 11:
			Piezamuseable cuatro = repo.getOne(id);
			//cuatro.setFotografiacuatro(file.getBytes());
			rutaAntiguo =cuatro.getFotografiacuatro();
			rutaNuevo = UPLOADED_FOLDER +String.valueOf(cuatro.getItemid().getItemid())+ FOTO_CUATRO+file.getOriginalFilename();
			cuatro.setFotografiacuatro(rutaNuevo);
			repo.save(cuatro);
		
			break;

		default:
			break;
		}
		
		if (rutaAntiguo!=null) {
			File fileBorrar = new File(rutaAntiguo);
        	
    		if(fileBorrar.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}
		}
		if(file!=null) {
			Path path = Paths.get(rutaNuevo);
			Files.write(path, file.getBytes());
			
		}

		return new ResponseEntity(file.getBytes(), httpHeaders, HttpStatus.OK).ok(file.getBytes());

	}

}
