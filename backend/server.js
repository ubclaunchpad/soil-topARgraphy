/* eslint no-console: 0 */

import express from 'express'
import cors from 'cors'
import { check, validationResult } from 'express-validator/check'

const app = express()

/* if (process.env.NODE_ENV !== 'test') {
	app.use(express.logger())
} */

app.use(express.json())
app.use(express.urlencoded({ extended: false }))

app.use(cors({ origin: true }))

const mapRouter = express.Router()

appRouter.post('/submitmap', [
	check('valid')
], (req, res) => {
	const errors = validationResult(req)
	if (!errors.isEmpty()) {
		return res.status(422).json({ errors: errors.mapped() })
	}
	...(req, res)
})

app.use('/map', mapRouter)

app.listen(WEB_PORT, () => {
	console.log(`Server up on http://localhost:${WEB_PORT} (development)`)
})


module.exports = app
