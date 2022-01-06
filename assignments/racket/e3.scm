(define my-eval
  (λ (s env)
    (cond ((symbol? s) (cadr (assoc s env)))
	  ((pair? s)
	   (let ((h (car s)))
	     (cond ((equal? h 'if)
		    (my-eval (if (my-eval (cadr s) env)
				 (caddr s)
				 (cadddr s))
			     env))
		   ((equal? h 'quote) (cadr s))
		   ((equal? h 'λ)
		    (let ((vars (cadr s))
			  (body (caddr s)))
		      (list '%closure vars body env)))
		   ((assoc h macro-list)
		    => (λ (x) (
		      my-eval ((cadr x) s) env)))
		   (else
		    (let ((vals (map (λ (t)
				       (my-eval t env))
				     s)))
		      (my-apply (car vals)
				(cdr vals)))))))
	  (else s))))

(define my-apply
  (λ (f args)
    (cond ((procedure? f) (apply f args))
	  ((and (pair? f) (equal? (car f) '%closure))
	   (let ((vars (cadr f))
		 (body (caddr f))
		 (env (cadddr f)))
	     (my-eval body (append (map list vars args)
				   env))))
	  (else (error "invalid function" f)))))

(define stdenv
  (list (list 'plus (λ (x y) (+ x y)))
	(list 'times (λ (x y) (* x y)))
	(list '= =)
	(list 'null? null?)
	(list 'head car)
	(list 'tail cdr)
	'(pi 3.141592653589793)))

(define macro-list
  (list (list 'let
	      (λ (s)
		(let ((clauses (cadr s))
		      (body (caddr s)))
		  (let ((vars (map car clauses))
			(vals (map cadr clauses)))
		    (cons (list 'λ vars body) vals)))))
	(list 'cond error)))
	
(my-eval '(let ((x 2) (y 3)) (plus x y)) stdenv)
(map car '((x 2) (y 3)))

;(map (λ (t)(my-eval t env)) s)


(my-eval 
(cadr
(assoc 'let
(list
(list 'let (cons (list 'λ '(x y) '(plus x y)) '(2 3))))))
stdenv)